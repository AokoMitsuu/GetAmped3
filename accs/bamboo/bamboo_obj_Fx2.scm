	� G   :�       �WOoE?'R|�e{Yg����D�mH[JR�j�wg��;�ݱ��� A�A+��K)n�Ǳ·��̮w�v�$-AĖ�~��߼��;d�ߎ�66Q�p��۽�؛V���0��2�K]�&��nN��ݬ4��Ywr���01�W�t���h��h�����ڽ5�3��PT<����X[}�>Q.٩�%lYQ��{-J�&Wl����ꩵ�F�^4�z��D����%x������E����9!!�σ�Y̫�0P�
0�k@][��/��E�m���CB� �s\�����"��u����¾�Ǿ�#Z%�k�0>2фt:�&�ۅ�`Q��ADd����kNZB�����miƲ^�����������J!9���?�mEUD�T��R���.yQGe��|�b7b�:xY��ր� ��Q��M��m�Xy-0�얢��ٌ�o�K(`V���8��V�&!٭��
��55�Q*�=>=��ǳ�;!���������ܒ,��_�m��ܫ�r.����xԠ�&�uL<͌�plŵ]Tn}��h`�v��l�Xus�,���
��6�Ǧ*d��~���C��f]�C�n��+��>I��C[Jd�������@�#�s��v��l݅N�N5�ָs��s�s%�ʹ^7G�Rۣ;����ʏ ����s��<U�<
9.�� ��JJ!>v�Cx��^Đ�{��~ ��7��V��L��0�2bx��}��"��h�iˌ�X���瓯�<y���7��|4|u��OǏ?���/FCYOr:�/N_=���[0�ǧa����ڄ����!�\�g<�r-��uǣ�)!�������uІ���G�Ѐ��nQ��BAY�*�UAM��q����6�͍�%4�\��,��1�������-A]��pm %�J�ŕ�~6�ڒ^��)@������5�MZ(��`A��PH��AV�}jO�,*�:����h��u��{^!�F]��$��0�9��
w\S��y�q&�c�DFr?��3'�����F|n� L"1������jf���b��EbI�Av����Ө@RE�����߿��|�����?l����n���-����oR&'ߔ{�Tţ��땚�L��mP�_*���� 3埔}�c�1�<��HJ=ճ�g�	o0�����-�.�����YMR��䒴U��֌:X�(��0�S#b4��b��Z!\��k!9o|��Akj0#�w�>h��dq��m����a�U������6�JC�^)a�����&�GY�_��+ܨ��Ռ	�6��y.�   �  Lga2/stage/AmpedScript; e Lkotori/data/KGLGenerationData; leafD !/accs/bamboo/bamboo_leaf_main.scm�    @       ?333?333?333              leaf2D !/accs/bamboo/bamboo_leaf_main.scm�    ?�      ?333?333?333              start damagehitdir broken 
setVisible (Z)V
 f o p size (F)V
 f r s?�  
 H 0 3 anime
 d w s@�   clearGenerate
 d z 1D  /accs/bamboo/bamboo_collapse.scm�            ?�  ?�  ?�               rndc (F)F
 H } ~ g hpb (FFF)V
 � � �C4   scale
 � � s=L�� generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � � setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � � start2D /accs/bamboo/bamboo_leaf.scm�    ����    ?�  ?�  ?�                
start_leaf (FF)V
 d w �A   >�� 	playSound
 d � 3D /efx/smoke/smoke_sand27.ptc�    ��33    >���>���>���C/��          crack11D /efx/stage/iwa_r/iwa_r.scm�    �@      ?L��?   ?L��             D /efx/stage/iwa_r/iwa_r.scm�    �@      ?L��?   ?L��C4            attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            �
�� � 	hit_goza2  d�  �     � 2-    ?��    �
�� �  d�  �     � 2-    ?���    �
�� �  d�  �     � 2 setJudgmentKind
 f � 3 /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/flash/glow01_white7.ptc�    ?�      ?�  @@  ?�                enableCollision
 f � p clearJudgmentKind
 f � 1 lockItemScript
 f � p lockTimeCount
 f � p vibe (FFFFI)V
 d � �=��� getArgv ()Ljava/lang/Object;
 f � � getPosition ()Lkotori/geom/Position;
 f � � Lkotori/geom/KMath; � getDir (FF)F
 � � � R2D
 � � ~ 	 � Q 	 � S  Lkotori/geom/Position; � dir	 � � �� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � � getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � rotate2
 d � �B�   ()Lkotori/geom/Vector3D;
 H  � D2R
 � � ~ sin
 H � ~ cos
 H � ~ (FFF)Lkotori/geom/Vector3D;
 � + �>��� getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � Lkotori/gfx/KGLScriptModel; � kill
 � � 1D  /accs/bamboo/bamboo_collapse.scm�            ?�  ?�  ?�               end2 startScript (Ljava/lang/String;)Z
 � � � 	crack_end<#�
C   yquake
 d � 3D /accs/bamboo/bamboo_leaf.scm�@   �       ?�  ?�  ?�                	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � �-    �L��?�  ��� � hit_be  d�  X     c d-    �L��?�ff��� �  d�  X     c dD /efx/flash/glow01_white6.ptc�@   �       ?�  @@  ?�          B�   
 f � 1 
stopScript
 f � 1 setBlend
 d � 3 setShadowState
 d � 3 setEdge
 f  p 	fadeColor
 d � start3D /efx/flash/glow01_white6.ptc�    ?       ?�  @@  ?�                f h g j g - 1  	i� k��  l 1  �*� qW*u� tW*d� vW*� qW*y� xW*� {W|L+�� � �W+�� b� �W**+�� �� �W*��� �W*� vW*��� �W*� �W*��� �W**��� �� �W**��� �� �W*��� �W*��� �W*��� �W*d� vW*� �W*� l� �W*� l� �W*�l� �W*�l� �W*�� �W�  m 1	 e*� �W*� �W*� �W*� �W*�\ ȶ �W*� �W*� �L*� �M+� �+� ĸ �� �,� ȸ �fF�:� �:%� �W*%�f� �W� �:�%� ׸ �j�%� ׸ �j� �W*� �:� 	� �W*�� �W*d� vW*d� vW*d� vW*d� vW*� �:*� �:� � �W� � �W*F� vW*���� ȶ �W*� �W*� �W*�l� �W*� �W*��� �W*��� �W*� �W*d� vW*,� vW*�� �W*,� vW*� �W*� �W�  n 1  �*� �W*� �W*� �W*� �W*� vW*� �W*� �W*�W* ȶW*� �L+� +� �W*� �M*� �N,� 
,� �W-� 
-� �W*� �W*,� vW*� �W*� �W� xj,@<��
?L��    >�ff?�  >�ff�  