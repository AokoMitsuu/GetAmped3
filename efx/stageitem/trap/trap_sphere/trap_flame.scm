	� G  /efx/stageitem/mdl/trap_base.kmd��       �WKo�F>K��BF�e�P�\7	P�41�r#×�hj)-L��r%K
�P4ȭ�4�EnE{.��6�z��,)QJ��ps������~��(�R��5�Ty}n��	�u|)ժV�0��2cKa���u���xXB��<u��G�����gup��1�o��"��=i2��>�E� *����Z�G#��z�lU+���H1�lU�?{��ӳ�����ٳ��痏^dg�g�y6��z�3Z^)W��׋�Aőa�\�M�@���wvn�ڣMR�p�>���3������1�ح5x0i����P�K迮>Y��RXk�nS׶�-c��`����Ur�['�Z $���Wd��H���%q��:��-#���3�<1x�+���ia�l��^�Nʾ����C�J⥩v��Ðm�ǶQ�]v��V��FK!o ݃B`�����1��>������揫���\��A��G(E�
�����0v]Ǹp�i��1��DQ��8Jđ�P�bM�*���Xn]G���~�%�Ě �X��5��T/f��!a���ߠa�VU�m)�)kW�tF$ ��aN����t�t�NOV��aX�sD�'�o۶]���A�W���޴��s�[%���㩒���zV[Wp�'IŃ�y�/R~a�I �9��
-�~a�⾠�s�UZ����� �w�iM\�F������g�Sd,}?^��f+ �5#ˈ"��7��B���o����p�zB6n����
vw���sŮ�_r�Y�"ɑ)�A,�,�y��V@�1B`$eyMC0|݃S^��;�a��^7�.T,̷����ثa�)Ë���L����h��Eq����4�,e�pLm���@`M�٦� )�裴;+vg�.&�Vm�j铛��_E��$��R�B��7f>]�J�F2�c�Lɳ��� -�>(x���ʺ,�Օ
$����e�r�5MW)ͦ��N�N��~��^d�o��s��X*�%:;}��=!���}fؖc�ǵ�m��4Wj�hn���Ei��#�+]q9��X�tMX$A��˘Z�A�����@o��|PT�ʛ�hz���c��J�s�V��eo���K�Ҷ.�kSA����
ok�N,�{�>o��?��z>b�m
��8�:.�B�K�sl��e?�X�xSw�|�,�w����'`~IBn7Q��׍#�Oh6��p�ֲ3�cզQ�F�h%�aǯ7��ئ� �U?qk�԰5F���]��zp �ja�\�|�%K��7;���
��n{o�A�˝7����@�t�t%,� ��놐~���׼����J_@�@���y)�Q�.M$�j�`�6�Z$<��5�����$����x��\IDÁL4J�h�`����~B1��Ly��Z��wLmIC��.�Y���� �aC��o��h�C^Eu��|j^��I^��ض�h9�V�t�C�X4��ןTb�   �  Lga2/stage/AmpedScript; e Lkotori/gfx/KGLScriptModel; sphere start equipped startattack hitstage reflect broken destroy getHPB ()Lkotori/geom/Vector3D; install tempInt (I)Ljava/lang/Integer;
 d s t setShadowState
 d v 3 setRenderType
 d x 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d z {D //efx/stageitem/trap/trap_sphere/trap_sphere.scm�    >L��    ?�  ?�  ?�               fireD ,/efx/stageitem/trap/trap_sphere/trap_efx.scm�            ?�  ?�  ?�               /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d z �D 2/efx/stageitem/trap/trap_sphere/fire_in_sphere.scm�    �L��    ?�  ?�  ?�              
 H 0 3 roll (Lkotori/geom/Vector3D;I)V
 f � �-    Dz       temp ()Ljava/lang/Object;
 d � � Ljava/lang/Integer; � intValue
 � � 2 isServer ()Z
 f � � getPosition ()Lkotori/geom/Position;
 f � � (FFF)Lkotori/geom/Vector3D;
 H  � 	 � Q 	 � R 	 � S  Lkotori/scp/KotoriScript; � getVector3DArray (I)[Lkotori/geom/Vector3D;
 � � � )([Ljava/lang/Object;ILjava/lang/Object;)V
 � + � 
itemScript ((Ljava/lang/String;ILjava/lang/Object;)V
 f � � 	playSound
 d � 3 explode
 d � 1 
setVisible (Z)V
 f � � kill
 f � 1 getItemVector
 f � q������ kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � Lkotori/geom/Position; � dir	 � �  Lkotori/geom/KMath; � fPI	 � � C4   	transform .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � getStageCollisionNormal
 f � � scale (F)Lkotori/geom/Vector3D;
 � � ��L�� getDir (Lkotori/geom/Vector3D;)F
 � � � 	getSubDir (FF)F
 � � �B�  -             changePickable
 f � �-             getArgv
 f � � '(Ljava/lang/Object;I)Ljava/lang/Object;
 � * �=��� changeAction
 f � 3�� kotori.geom.Position                 setPosition (Lkotori/geom/Position;)V
 f � � rotate (FFF)V
 f � �D /efx/flash/glow01_red12.ptc�    >�=q    ?333?333?333            AD /efx/flash/glow01_red12.ptc�    >�=q    ?333?333?333            AD /efx/flash/glow01_red12.ptc�    >�=q    ?L��?L��?L��            AD /efx/flash/glow01_red12.ptc�    >�=q    ?fff?fff?fff            AD 5/efx/stageitem/trap/trap_sphere/trap_sphere_white.scm�    >L��    ?�  ?�  ?�              D /efx/flash/glow01_red12.ptc�    >�=q    ?fff?fff?fff            AD /efx/flash/glow01_red12.ptc�    >�=q    ?fff?fff?fff            A g
 � � 1D /efx/stageitem/ripple02_red.ptc�    >�=q    ?L��?L��?L��            A��  � hit_fb   d�  ,       �       ,�>�         sv
 d � q��?�              A   D /efx/fire/fire_trap.scm�    ?���    ?�  ?�  ?�      ´        Lga2/stage/atk/ShootAttack; � vx	 � �  vy	 � �  vz	 � �  
trapAttack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � � (IIF)V
 d � � finishAllKGL
 f � 1 f h g	 i 1  *� W*� uW�  j 1  '*� wW*� yW*}~� |� *~� |W*�� �W�  k 1  *� �W*�� �W*� uW*� W�  l 1  g*� �L+� �� T*� �� M*� �M� �N-,� �� �-,� �� �-,� �� �*� �:*-� �W**� � �W*r� �W*�� �W�  m 1  *� �W*{� �W�  n 1  *� W�  o 1  %*� �W*&� �W*� �W*� �W*d� �W*� �W�  p q  �*� �� ��� P*� �� ��� D*� �� ��� 8�� �N�:*� �� �� �n�j� �:-� �N-M*-� �L� *� �ȶ �M*,� �L,� �+� ˸ β �nb�j8+� ��� ++� �v� �+� ��� ++� �v� �+� �+� �b�j8�:� �� ��  r 1	 �*� �W*�� �W**� �� �L**� �� �M,� �ؕ� 	*� �W�N-+� �� �-+� �� �-+� �� �*-� �W*,� �,� �� �W*� wW*� yW*� �W*� �W*� �W*� �W*� �W*� �W*� �W*� �W*� �W*� �W* ȶ �W� � �W*d� �W*� �W*� �W*� �W*�� �W�:*� �:�:,� �,� �� �:�� �:� �:6� Z*� �W� �� �� �� �� �� �*� �Wp� *p��jf� �W* �� �W�W ȡ��*� �W*� W� ��zb    >k�    >��R>��R>��R�  