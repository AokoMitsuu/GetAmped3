	� G   F�       �Vmo�6�����EvY~kZx.�-[�fݲ:�W��(��$
$��-��{䉑jh��(�����fE�HJG��4e�"/Y��$/������祐�l��1�4��N�)y9�7��ɩCk�X�޶3�6�H�-��-/�G�R�g��(R{���g�LD�7��xJO����@������"��L�"���g8%Uʛ�VH1���X<�Cͼ�%��Mh��o�N��.NsV�EYJA��o��څ�C?��7����2F�+�4^��'B�7��g{��q��(R�X��[�G!�-��#/�n�@EywF6�����	x�͈�hƼ0��͎>�ZȺ����A���r
�R(�@0FH�	�[mD9݈����l>F�Mȱ�iZ��p]�|#�u����P@��.�7���;����9I�`a�B�'bç�Q�'�8/vY�3򁅽�Нx���� �9�& ��2}�����%�Y���v�,��$.��yo@�Cg�. �Z��ڭ��`��TǢKb.W�O`�՛@n�wk��G��
�����a-U����N�Rr�z��sn/�d�<t���t�]{��:(�lX.�@��eB��O��gm�����Ah(a �ad�H��k�c�;�+\��|r �._�M��B[��`���}0�[��z��S��M~߳>r^xpS-�7�L1Ď��>sږst`u�7�2QT��Q~&��w?ׄk�]A=�j]l�1w��U>~ݏ���N����`˳=C�IM�3|g|��p�t�5��捹M���?��⏦)�2#�ݏ�s�K�+�B#��U��]�uŗa�)X-�UT��.�V�u�Q��ut�{���4�ZR�d"dN���B,�B��)���h�4F�h(Dm���)���Л�͒����ܽ!2B����Cu@�m���=,mEC,�yh�>�X��$L��U���&�S"J�d�����	�ͼ%3�*/��ֺvse�Pͨ34o�A[U��w��9��Z�-������ٽ2���Cbڞ3'Ϥn� �����5-aJ�h\M��8Y��i9u�PZ����8z�h5   l  Lga2/stage/AmpedScript; e start approachfloatconstant broken lockItemScript (ZZ)V
 f j k finishAllConnections
 d m 1
 H 0 3 setShadowState
 d p 3  setJudgmentApproachFloatConstant (FFF)V
 f r sA   >��� clearGenerate
 d w 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d y z setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d | }D '/accs/double_sword_and_wing/sword_l.scm�            ?333?333?333C4  �4      D #/accs/double_sword_and_wing/efx.scm�    >���>���?   ?   ?   C4  �4        mahojin lockTimeCount (Z)V
 f � � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � Lkotori/gfx/KGLScriptModel; � startScript (Ljava/lang/String;)Z
 � � � mahojin_end getArgv ()Ljava/lang/Object;
 f � � getPosition ()Lkotori/geom/Position;
 f � ��� kotori.geom.Quaternion?�                sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D (F)F
 � � � Ljava/lang/Math; � round (F)I
 � � � ()F
 f � �C4  C�  �4  	 � R  length
 � � � (FF)F
 � � � min
 � � �B4   Lkotori/geom/Quaternion; � clone
 � � � getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � rotate (Lkotori/geom/Quaternion;I)V
 f � � kill
 � � 1 	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � 	playSound
 d � 3 attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-        >���� ��D ./accs/double_sword_and_wing/fx_shoot_sword.scm�            ?�  ?�  ?�  C4  B�      � hit_bb  d�   �    ]� �       ,�>�         clearJudgmentKind
 f � 1
 f j � end_add
 f � 1 
stopScript
 f � 1 f  g 1  S*� lW*� nW*2� oW*� qW*� lW*ubv� tW*� xW**g� {� ~W**��� {� ~W* ȶ oW�  h 1 J*� lW*� �W*� �L*� �M*� oW,� 
,�� �W*� �N*� �:�:-� �-� �W-� �� �� ��8*� �� �� ��8��� 
�f8��� 
�f8f8��� �f8� ��� 
�b8-� �8	-� �-� �8

	� �� �� �Zd�8�� #bj8bj�� ��nj8b8� �:� �W*2� �W*2� oW+� +� �W*� �W*i� �W*�ȶ �W*� �W*X� oW*� W�  i 1  O*� �W*� lW*� �W*� �L*� �M*� oW+� 
+̶ �W,� 
,�� �W* ȶ oW*� �W*� �W� ��r            =���=���=���� � changemodel� ,/accs/gorgeous_knife/gorgeous_knife_beam.mot